import React from 'react';
import './card.scss';

interface IProps {
  className?: string;
  title?: string;
  center?: boolean;
  children: React.ReactNode;
}

export const Card: React.FC<IProps> = ({
  className,
  title,
  center,
  children,
}) => {
  return (
    <div
      className={`card ${center ? 'card--centered' : ''} ${className || ''}`}
    >
      {title && <h2>{title}</h2>}
      <div className="card__content">{children}</div>
    </div>
  );
};
